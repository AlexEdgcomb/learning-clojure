(ns test)

(defn factorial [n]
  (loop [cnt n, acc 1]
    (if (zero? cnt)
        acc
        (recur (dec cnt) (* acc cnt)))))

(defn -main []
  (println (factorial 10)))
