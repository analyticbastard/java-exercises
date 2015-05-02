(ns max-subsequence)


(def ex-bestmiddle [-1 -3 -2 1 2 -1 3 -2 2 1 -1 -3])
(def ex-bestleft [-1 3 2 1 -2 -5 -3 -3 2 3 -1 -3])
(def ex-bestall [5 -3 -2 2 2 -1 3 -2 2 1 -1 3])

(defn best [myseq]
  (let [N (count myseq)]
    (case N
      1 {:l 0 :r 0 :sl [] :sr [] :seq myseq :sum (first myseq)}
      2 (if (and (> (first myseq) 0) (> (second myseq) 0))
          {:l 0 :r 0 :sl [] :seq myseq :sr [] :sum (apply + myseq)}
          (if (< (first myseq) (second myseq))
            {:l (first myseq) :r 0 :sl [(first myseq)] :sr [] :seq (rest myseq) :sum (second myseq)}
            {:l 0 :r (second myseq) :sl [] :sr (rest myseq) :seq [(first myseq)] :sum (first myseq)}))
      (let [n (Math/round (double (/ N 2)))
            m (- N n)
            s1 (take n myseq)
            s2 (take-last m myseq)
            m1 (best s1)
            m2 (best s2)
            sum2 (+ (:sum m1) (:sum m2))
            cost (+ (:r m1) (:l m2))
            sumt (+ sum2 cost)
            ]
        (if (> sumt (max (:sum m1) (:sum m2)))
          {:l (:l m1) :r (:r m2) :sl (:sl m1) :sr (:sr m2) :seq (concat (:seq m1) (:sr m1) (:sl m2) (:seq m2)) :sum sumt}
          (if (> (:sum m1) (:sum m2))
            {:l (:l m1) :r (+ (:r m1) (:l m2) (:sum m2) (:r m2)) :sl (:sl m1) :sr (concat (:sr m1) (:sl m2) (:seq m2) (:sr m2)) :seq (:seq m1) :sum (:sum m1)}
            {:l (+ (:l m1) (:r m1) (:sum m1) (:l m2)) :r (:r m2) :sl (concat (:sl m1) (:seq m1) (:sr m1) (:sl m2)) :sr (:sr m2) :seq (:seq m2) :sum (:sum m2)}))
        ))
    ))


(println (best ex-bestmiddle))