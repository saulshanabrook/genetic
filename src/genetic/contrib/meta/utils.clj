(ns genetic.contrib.meta.utils)

; http://stackoverflow.com/a/19375661/907060
(defn apply-map [f m]
  (apply f (apply concat m)))
