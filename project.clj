(defproject genetic "0.1.0"
  :description "Framework for running genetic algorithms"
  :url "https://github.com/saulshanabrook/genetic"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [roul "0.2.0"]]
  :main ^:skip-aot genetic.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :test {:plugins [[jonase/eastwood "0.1.0"]]}})
