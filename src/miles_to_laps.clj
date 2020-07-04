(ns miles-to-laps)

(defn get-float [] (.nextFloat (java.util.Scanner. System/in)))

(defn miles-to-laps [miles] (* miles 4))

(defn -main []
  (do
    (print "Enter miles: ")
    (flush)
    (println(str "Laps: " (format "%.2f" (miles-to-laps (get-float)))))
  )
)