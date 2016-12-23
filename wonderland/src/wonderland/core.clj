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

(defn old-alice-is [in out]
  (if (empty? in)
    out
    (alice-is (rest in)
              (conj out (str "Alice is " (first in))))))

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


