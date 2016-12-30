(ns cheshire-cat.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [cheshire.core :as json]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/cheshire-cat" []
       {:status 200
        :headers {"Content-Type" "application/json; charset=utf-8"}
        :body (json/generate-string {:name "Cheshire Cat" :status :grinning})})
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))


(comment

  (json/generate-string {:name "Cheshire Cat" :state :grinning})
  (json/parse-string "{\"name\":\"Cheshire Cat\",\"state\":\"grinning\"}")
  (json/parse-string "{\"name\":\"Cheshire Cat\",\"state\":\"grinning\"}" true)

  )
