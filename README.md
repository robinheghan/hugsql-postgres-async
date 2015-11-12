# postgres.async adapter for HugSQL

## Setup

Add the following to your `project.clj`:

[![Clojars Project](http://clojars.org/skinney/hugsql-adapter-postgres-async/latest-version.svg)](http://clojars.org/skinney/hugsql-adapter-postgres-async)

Then you have to install the adapter:

```clojure
(ns core
  (:require [hugsql.core :as hugsql]
            [hugsql.adapter.postgres-async :as ps-adapter]))

(hugsql/def-db-fns "fns.sql")
(hugsql/set-adapter! (ps-adapter/hugsql-adapter-postgres-async))
```

## Usage

The [HugSQL](https://github.com/layerware/hugsql) documentation is the definitive guide. The difference is that this adapter returns `core.async` channels with the result or an exception. This adapter uses `postgres.async`, so naturally it only works with postgres.
