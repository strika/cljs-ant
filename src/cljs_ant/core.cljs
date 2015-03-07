(ns ^:figwheel-always cljs-ant.core
  (:require [cljs-ant.ant :refer [move-ant]]
            [cljs-ant.world-view :refer [max-ant-x max-ant-y
                                         draw-world world-width world-height]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(def refresh-rate 200) ; miliseconds

(defn random-ant-direction []
  :up)

(defn generate-ant [max-x max-y]
  {:x (Math/round (rand max-ant-x))
   :y (Math/round (rand max-ant-y))
   :direction (random-ant-direction)})

(defn generate-world []
  {:live-cells #{}
   :ant (generate-ant world-width world-height)})

(defonce world (atom (generate-world)))

(defn update-world [world]
  (swap! world move-ant))

(defn loop-world []
  (update-world world)
  (draw-world (:live-cells @world)))

(defonce create-world
  (do
    (. js/window (setInterval (fn [] (loop-world)) refresh-rate))))

