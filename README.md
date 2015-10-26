# HugSQL adapter for postgres.async

## Setup

Add the following to your `project.clj`:

[![Clojars Project](http://clojars.org/differ/latest-version.svg)](http://clojars.org/skinney/hugsql-adapter-postgres-async)

Then you have to install the adapter:

```
(ns core
  (:require [hugsql.core :as hugsql]
            [hugsql.adapter.postgres-async :as ps-adapter]))

(hugsql/set-adapter! (ps-adapter/hugsql-adapter-postgres-async))
(hugsql/def-db-fns "fns.sql")
```

## Usage

The [HugSQL](https://github.com/layerware/hugsql) is the definetive guide. The only difference is that this adapter returns core.async channels, and that this adapter only works with postgres.

Exceptions from the database are returned on the channel. Exceptions thrown by hugsql are not.
