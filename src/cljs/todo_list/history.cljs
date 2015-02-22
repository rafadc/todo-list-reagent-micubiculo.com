(ns todo-list.history
  (:require [goog.history.EventType :as EventType]
            [goog.events :as events]
            [secretary.core :as secretary :include-macros true])
  (:import goog.History))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))
