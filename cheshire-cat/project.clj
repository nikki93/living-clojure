(defproject cheshire-cat "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.3.1"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [cljs-http "0.1.18"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [enfocus "2.1.0"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.0.3"]]

  :ring
  {:handler cheshire-cat.handler/app}

  :cljsbuild
  {:builds [{:source-paths ["src-cljs"]
             :compiler {:output-to "resources/public/main.js"
                        :optimizations :whitespace
                        :pretty-print true}}]}

  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
