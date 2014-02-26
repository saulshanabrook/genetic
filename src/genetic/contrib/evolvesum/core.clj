(ns genetic.contrib.evolvesum.core
  "Evolve a vector of 100 zeros and ones that sums to a particular number.

  from https://gist.github.com/lspector/1291789"
  (:require
    [genetic.core :as genetic]
    [clojure.math.numeric-tower :as math]))

;; An individual is a vector of 100 random bits.

(defn new-individual
  []
  (vec (repeatedly 100 #(rand-int 2))))

;; An individual is mutated by possibly flipping a random bit.

(defn mutate
  [individual]
  (assoc individual (rand-int 100) (rand-int 2)))

(defn score
  [target-number]
  (fn [individual]
    (let [error (math/abs (- (reduce + individual) target-number))]
      (/ 1.0 error))))

(defn evolvesum
  [target-number]
  (partial genetic/evolve :new-individual new-individual :mutate mutate :fitness (score target-number)))

(defn -main []
  ((evolvesum 73)))
