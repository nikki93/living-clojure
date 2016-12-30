(ns cheshire-cat.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [clojure.browser.repl :as repl]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [enfocus.core :as ef]
            [enfocus.events :as ev]))

(defn ^:export init []
  (repl/connect "http://localhost:9000/repl")
  (go
    (let [{{:keys [name status]} :body} (<! (http/get "/cheshire-cat"))]
      (ef/at "#cat-name" (ef/content name)
             "#status" (ef/do->
                        (ef/content status)
                        (ef/set-style :font-size "500%")))
      (ef/at "#button1" (ev/listen :click #(js/alert "bye!"))))))
