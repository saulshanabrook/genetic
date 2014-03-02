(ns genetic.contrib.regex.t-operations
  (:require
    [clojure.test :refer :all]
    [midje.sweet :refer :all]
    [genetic.contrib.regex.operations :as operations]))

(facts "about `mutate-add`"
  (count (operations/mutate-add "a" #{})) => 1)

#_(facts "about `mutate-delete`"
  (operations/mutate-delete #{"a"}) => #{})

