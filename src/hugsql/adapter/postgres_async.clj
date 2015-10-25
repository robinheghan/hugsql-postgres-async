(ns hugsql.adapter.postgres-async
  (:gen-class)
  (:require [hugsql.adapter :as adapter]
            [postgres.async :as ps]
            [clojure.core.async :refer [go <!]]))

(deftype HugsqlAdapterPostgresAsync []

  adapter/HugsqlAdapter
  (execute [this db sqlvec options]
    (ps/execute! db sqlvec))

  (query [this db sqlvec options]
    (ps/query! db sqlvec))

  (result-one [this result options]
    (go
      (let [res (<! result)]
        (if (instance? Exception res)
          res
          (first res)))))

  (result-many [this result options]
    result)

  (result-affected [this result options]
    (go
      (let [res (<! result)]
        (if (instance? Exception res)
          res
          (first res))))

  (result-raw [this result options]
    result))

(defn hugsql-adapter-postgres-async []
  (->HugsqlAdapterPostgresAsync))
