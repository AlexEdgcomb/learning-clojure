(ns min-and-max)

(defn get-int []
  (.nextInt (java.util.Scanner. System/in)))

(defn -main []
  (let [a (get-int) b (get-int) c (get-int)] (do
    (println (str "largest: " (max a b c)))
    (println (str "smallest: " (min a b c)))
  ))
)