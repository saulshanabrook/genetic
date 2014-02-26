(ns genetic.utils)

(defn now []
  (. java.lang.System (clojure.core/currentTimeMillis)))

(defn seconds-elapsed [previous]
  (float (/ (- (now) previous) 1000.0)))
