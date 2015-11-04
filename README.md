# HugSQL adapter for postgres.async

## Setup

Add the following to your `project.clj`:

[![Clojars Project](http://clojars.org/skinney/hugsql-adapter-postgres-async/latest-version.svg)](http://clojars.org/skinney/hugsql-adapter-postgres-async)

Then you have to install the adapter:

```clojure
(ns core
  (:require [hugsql.core :as hugsql]
            [hugsql.adapter.postgres-async :as ps-adapter]))

(hugsql/set-adapter! (ps-adapter/hugsql-adapter-postgres-async))
(hugsql/def-db-fns "fns.sql")
```

## Usage

The [HugSQL](https://github.com/layerware/hugsql) is the definitive guide. The only difference is that this adapter returns `core.async` channels and only works with postgres.

Only exceptions from the database are returned on the channel. Exceptions thrown by `hugsql` are handled ... how - TBD?.
