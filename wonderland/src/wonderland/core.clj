(ns wonderland
  (:require [clojure.set :as s]))


;; 1, 2

(defn common-fav-foods [foods1 foods2]
  (let [food-set1 (set foods1)
        food-set2 (set foods2)
        common-foods (s/intersection food-set1 food-set2)]
    (str "Common foods: " common-foods)))


(defn grow [name direction]
  (if (= direction :small)
    (str name " is growing smaller")
    (str name " is growing bigger")))


(def adjs ["normal" "too small" "too big" "swimming"])

(comment

  (defn old-alice-is [in out]
    (if (empty? in)
      out
      (alice-is (rest in)
                (conj out (str "Alice is " (first in))))))

  )

(defn alice-is [input]
  (loop [in input out []]
    (if (empty? in)
      out
      (recur (rest in)
             (conj out (str "Alice is " (first in)))))))


(comment

  (common-fav-foods [:jam :brownies :toast]
                    [:lettuce :carrots :jam])

  (true? true)

  (true? false)


  (= :drinkme :drinkme)

  (= '(:drinkme :bottle) [:drinkme :bottle])

  (empty? [:a :b])


  (if nil "it is true" "it is false")


  (grow "Alice" :big)

  (repeat 3 "rabbit")

  (repeatedly 5 #(rand-int 50))

  (take 3 (cycle [:a :b]))


  (alice-is adjs)


  (defn countdown [n]
    (if (= n 0)
      n
      (countdown (- n 1))))

  (defn countdown [n]
    (if (= n 0)
      n
      (recur (- n 1))))

  (countdown 3)

  (countdown 100000)


  (map #(str %) animals) ; NOTE(nikki): Could've just written as (map str animals)
  (class (map #(str %) animals))

  (take 4 (map #(str %) (range)))


  (def animals [:mouse :duck :dodo :lory :eaglet])


  (def animals ["mouse" "duck" "dodo" "lory" "eaglet"])

  (def colors ["brown" "black" "blue" "pink" "gold"])

  (defn gen-animal-string [animal color]
    (str color "-" animal))

  (map gen-animal-string animals colors)

  (map gen-animal-string animals (cycle ["red" "green"]))


  (reduce (fn [r x] (+ r (* x x)))
          0
          [1 2 3])

  (reduce (fn [r x] (if (nil? x) r (conj r x)))
          []
          [:a nil :b :c nil nil :d])


  (into {} [[:a 1] [:b 2] [:c 3]])
  (into [] {:a 1 :b 2 :c 3})

  )


;; 3

(def who-atom (atom :caterpillar))

(defn change [who]
  (case who
    :caterpillar :chrysalis
    :chrysalis :butterfly
    :butterfly))

(assoc {} :a 3)


(comment

  who-atom

  @who-atom

  (reset! who-atom :caterpillar)

  (swap! who-atom change)

  )


(def who-agent (agent :caterpillar))

(comment

  @who-agent

  (send who-agent change)

  )



;; 4

(comment


  (class "caterpillar")

  (.toUpperCase "caterpillar")

  (.indexOf "caterpillar" "pillar")


  )


(ns caterpillar.network
  (:import (java.net InetAddress)))

(comment

  (.getHostName (InetAddress/getByName "localhost"))


  (doto (StringBuffer. "Who ")
    (.append "are ")
    (.append "you?")
    (.toString))

  )


(defmulti who-are-you class)

(defmethod who-are-you java.lang.String [input]
  (str "String - who are you? " input))

(defmethod who-are-you clojure.lang.Keyword [input]
  (str "Keyword - who are you? " input))

(defmethod who-are-you java.lang.Long [input]
  (str "Number - who are you? " input))

(comment

  (who-are-you :alice)

  )


(defprotocol Edible
  (bite-right-side [this])
  (bite-left-side [this]))

(defrecord WonderlandMushroom [color height]
  Edible
  (bite-right-side [this]
    (str "The " color " bite makes you grow bigger"))
  (bite-left-side [this]
    (str "The " color " bite makes you grow smaller")))

(defrecord RegularMushroom [color height]
  Edible
  (bite-right-side [this]
    (str "The " color " bite tastes bad"))
  (bite-left-side [this]
    (str "The " color " bite tastes bad too")))

(def alice-mushroom (WonderlandMushroom. "blue dots" "3 inches"))
(def reg-mushroom (RegularMushroom. "brown" "1 inches"))

(comment

  (bite-right-side alice-mushroom)
  (bite-left-side alice-mushroom)

  (bite-right-side reg-mushroom)
  (bite-left-side reg-mushroom)

  )


;; 8

(comment (when (= 2 2) (println "It is four!")))

(defn hi-queen [phrase]
  (str phrase ", so please your Majesty."))

(defn alice-hi-queen []
  (hi-queen "My name is Alice"))

(comment (alice-hi-queen))

(defn march-hare-hi-queen []
  (hi-queen "I'm the March Hare"))

(comment (march-hare-hi-queen))

(defn white-rabbit-hi-queen []
  (hi-queen "I'm the White Rabbit"))

(comment (white-rabbit-hi-queen))

(defn mad-hatter-hi-queen []
  (hi-queen "I'm the Mad Hatter"))

(comment (mad-hatter-hi-queen))

(comment
  (defmacro def-hi-queen [name phrase]
    (list 'defn name []
          (list 'hi-queen phrase))))

(defmacro def-hi-queen [name phrase]
  `(defn ~(symbol name) []
     (hi-queen ~phrase)))

(def-hi-queen alice-hi-queen "My name is Alice")
(def-hi-queen march-hare-hi-queen "I'm the March Hare")
(def-hi-queen white-rabbit-hi-queen "I'm the White Rabbit")
(def-hi-queen mad-hatter-hi-queen "I'm the Mad Hatter")

