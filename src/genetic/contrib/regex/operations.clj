(ns genetic.contrib.regex.operations
  (:require
    [roul.random :as random]))


(defn new-part
  [possible-chars]
  "Returns a new regex substring made of `possible-chars` plus `.`, `^`, and
  `$`.

  Length of substring is gaussian dist. around 1.
  `^` at  beginning is 50%
  `$` at end is 50%"
  (let [length (random/rand-gaussian-int 1 5 Double/POSITIVE_INFINITY 1)
        random-char #(random/rand-nth-weighted
          [[(rand-nth (apply vector possible-chars)) 1]
           ["." 1]])
        prefix (random/rand-nth-weighted
          [["^" 0.5]
           ["" 0.5]])
        suffix (random/rand-nth-weighted
          [["$" 0.5]
           ["" 0.5]])]
        (str prefix (apply str (repeatedly length random-char)) suffix)))

(defn mutate-add [possible-chars regex-split]
  (conj regex-split (new-part possible-chars)))

(defn mutate-delete [regex-split]
  (conj (disj regex-split (rand-nth (apply vector regex-split))))

(defn mutate-modify [possible-chars regex-split]
  (mutate-add possible-chars (mutate-delete regex-split))))

(defn mutate-operation [possible-chars regex-split]
  (let [operations [(partial mutate-add regex-split)
                    mutate-delete
                    (partial mutate-modify regex-split)]
        chosen-operation (rand-nth operations)]
    (chosen-operation regex-split)))
