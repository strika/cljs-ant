(ns ^:figwheel-always cljs-ant.core
  (:require [cljs-ant.world-view :refer [draw-world world-width world-height]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(def refresh-rate 200) ; miliseconds

(defn random-ant-direction []
  :up)

(defn generate-ant [max-x max-y]
  {:x (Math/round (rand max-x))
   :y (Math/round (rand max-y))
   :direction (random-ant-direction)})

(defn generate-world []
  {:live-cells []
   :ant (generate-ant world-width world-height)})

(defonce world (atom (generate-world)))

(defn update-world [world]
  (swap! world assoc :live-cells [{:x 100 :y 100} {:x 100 :y 200}]))

(defn loop-world []
  (update-world world)
  (draw-world (:live-cells @world)))

(defonce create-world
  (do
    (. js/window (setInterval (fn [] (loop-world)) refresh-rate))))

