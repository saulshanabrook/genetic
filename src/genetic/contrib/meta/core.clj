(ns genetic.contrib.meta.core
  (:require
    [roul.random :as random]
    [genetic.core :as genetic]
    [genetic.contrib.evolvesum.core :as evolvesum]
    [genetic.contrib.meta.utils :as utils]))

(defn new-individual
  []
  {:popsize (rand-int 100) :mutate-rate (rand-int 1000)})

(defn mutate
  [individual]
  (let [update-key (rand-nth (keys individual))
        update-function #(random/rand-gaussian-int % 1 Double/POSITIVE_INFINITY 1)]
    (update-in individual [update-key] update-function)))

(defn crossover
  [i j]
    (merge-with #(rand-nth [%1 %2]) i j))

(defn fitness-for-evolve
  [evolve-func]
    (fn [evolve-map]
      (let [[end-fitness duration] (utils/apply-map evolve-func evolve-map)]
        (/ end-fitness (float duration)))))

(defn meta-evolve
  [evolve-func]
  (partial genetic/evolve :popsize 10 :new-individual new-individual :crossover crossover :mutate mutate :fitness (fitness-for-evolve evolve-func)))

(defn -main []
  ((meta-evolve (evolvesum/evolvesum 73))))
