(ns genetic.contrib.symbolicregression.core
  "Evolve a function that fits a particular set of [x y] pairs.

  from https://github.com/lspector/gp/blob/master/src/gp/evolvefn.clj"
  (:require
    [genetic.core :as genetic]
    [genetic.contrib.symbolicregression.evolvefn :as evolvefn]))

(defn fit
  [source-data]
  (fn [individual]
    (let [error (evolvefn/error individual source-data)]
      (/ 1.0 error)))))

 (defn symbolicregression
  [source-data]
  (genetic/evolve :new-individual #(evolvefn/random-code 4) :mutate evolvefn/mutate :crossover evolvefn/crossover :fitness (fit source-data)))

(defn -main []
  (symbolicregression evolvefn/target-data))
