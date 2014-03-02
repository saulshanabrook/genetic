(ns genetic.core
  (:require
    [roul.random :as random]
    [genetic.utils :as utils]))

(defn generate [population mutate crossover])

(defn evolve [& {:keys [new-individual mutate crossover fitness popsize max-generations acceptable-fitness max-seconds mutate-weight clone-weight crossover-weight logging-prefix logging]
                 :or {new-individual nil
                      mutate nil
                      crossover nil
                      mutate-weight 1/2
                      clone-weight 1/4
                      crossover-weight 1/4
                      fitness nil
                      acceptable-fitness Double/POSITIVE_INFINITY
                      popsize 100
                      max-generations Double/POSITIVE_INFINITY
                      max-seconds Double/POSITIVE_INFINITY
                      logging-prefix ""
                      logging true}}]
  (assert (not (zero? popsize)))
  (let [start-time (utils/now)
        log (fn [& args] (if logging (apply println logging-prefix args)))]
    (loop [generation 1
           population (repeatedly popsize new-individual)
           population-history []]
      (log  "======================")
      (log  "Generation:" generation)
      (let [best (apply max-key fitness population)
            best-fitness (fitness best)
            duration (utils/seconds-elapsed start-time)]
        (log "Best fitness:" best-fitness)
        (log  "Best solution:" best)
        (log  "Running for" (float duration) "seconds")
        (if (some  true? [(= generation max-generations)
                          (>= best-fitness acceptable-fitness)
                          (>= duration max-seconds)])
          (do (log  "Finished:" best)
            [best-fitness duration population-history])
          (recur
            (inc generation)
            (let [child (fn [] (random/rand-nth-weighted (for [individual population] [individual (fitness individual)])))
                  new-generators [[#(mutate (child)) mutate-weight]
                                  [child clone-weight]
                                  [#(crossover (child) (child)) (if crossover crossover-weight 0)]]]
              (concat
                  (repeatedly popsize (#(random/rand-nth-weighted new-generators)))))
            (conj population-history [generation best-fitness])))))))
