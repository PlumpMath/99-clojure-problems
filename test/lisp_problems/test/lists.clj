(ns lisp_problems.test.lists
  (:use [lisp-problems.lists])
  (:use [clojure.test]))

(deftest test-returns-last-element-of-list
  "Should return the last element of a list"
  (let [x (list 1 2 3 4 5)]
    (is (= (my-last x) 5))
    (is (= (my-last-2 x) 5))
    (is (= (my-last-3 x) 5))))

(deftest test-returns-last-but-one-of-list
  "Should return the last but one element of a list"
  (let [x (list 1 2 3 4 5)]
    (is (= (my-but-last x) 4))
    (is (= (my-but-last-2 x) 4))
    (is (= (my-but-last-3 x) 4))))

(deftest test-get-n-in-list
  "Get the nth element in a list"
  (let [x (list 1 2 3 4 5 6 7)]
   (is (= (element-at x 2) 3))
   (is (= (element-at-2 x 3) 4))))

(deftest test-counts-elements-in-list
  "Count the number of elements in a list"
  (let [x (list 1 2 3 4)
        y (list 9 8 7 6 5 4 3 2 1)]
    (is (= (number-of-elements y) 9))
    (is (= (number-of-elements-2 x) 4))))


