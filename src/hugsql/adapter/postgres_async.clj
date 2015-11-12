(ns hugsql.adapter.postgres-async
  (:gen-class)
  (:require [clojure.core.async :refer [go <! chan put! close!]]
            [hugsql.adapter :as adapter]
            [postgres.async :as ps]
            [clojure.string :as str]))

(def ^:private ^:const params-regex #"(?<!\")\?(?!\")")

(defn- postgresify-query [original-query]
  (loop [q original-query
         c 1]
    (let [new-q (str/replace-first q params-regex (str "\\$" c))]
      (if (= q new-q)
        q
        (recur new-q (inc c))))))

(defn- get-one [c]
  (go
    (let [res (<! c)]
      (if (instance? Exception res)
        res
        (first res)))))

(deftype HugsqlAdapterPostgresAsync []
  adapter/HugsqlAdapter
  (execute [this db sqlvec options]
    (let [postgres-vec (update sqlvec 0 postgresify-query)]
      (ps/execute! db postgres-vec)))

  (query [this db sqlvec options]
    (let [postgres-vec (update sqlvec 0 postgresify-query)]
      (ps/query! db postgres-vec)))

  (result-one [this result options]
    (get-one result))

  (result-many [this result options]
    result)

  (result-affected [this result options]
    (get-one result))

  (result-raw [this result options]
    result)

  (on-exception [this exception]
    (let [c (chan 1)]
      (put! c exception)
      (close! c)
      c)))

(defn hugsql-adapter-postgres-async []
  (->HugsqlAdapterPostgresAsync))
