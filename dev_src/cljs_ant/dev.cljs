(ns cljs-ant.dev
    (:require
     [cljs-ant.core]
     [figwheel.client :as fw]))

(fw/start {
  :on-jsload (fn []
               ;; (stop-and-start-my app)
               )})
