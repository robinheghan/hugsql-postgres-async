(defproject skinney/hugsql-adapter-postgres-async "0.1.0-SNAPSHOT"
  :description "postgres.async hugsql adapter"
  :url "https://github.com/Skinney/hugsql-async"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [com.layerware/hugsql-adapter "0.2.2"]
                 [alaisi/postgres.async "0.6.0"]])
