(ns word-frequencies)
(require '[clojure.string :as str])

(defn -main []
  (let [words (str/split (read-line) #" ") counts (frequencies words)] (doseq [word words]
    (println (str word " " (get counts word))))))
