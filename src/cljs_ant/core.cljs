(ns ^:figwheel-always cljs-ant.core
    (:require))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(def refresh-rate 10) ; miliseconds

(defonce world (atom {:paper (js/Raphael 0 0 500 500)}))

(defn draw-world []
  (let [paper (:paper @world)]
    (. paper clear)
    (-> (. paper (circle 50 50 10))
        (. (attr "fill" "#00f")))))

(defonce create-world
  (do
    (. js/window (setInterval (fn [] (draw-world)) refresh-rate))))

