(ns todo-list.views.main
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce tasks (atom []))

(defn- add-task [text]
  (swap! tasks #(conj % {:text text, :status :notdone})))

(defn- show-task [task]
  "Show a single task"
  ^{:key task} [:div {:class (str "task task-" (name (:status task)))}
                [:b (:text task)]])

(defn- new-task-input []
  "The input box for adding tasks"
  [:div
   [:input {:type "text" :id "newTaskText"}]
   [:input {:type "button" :value "Add task"
            :on-click #(add-task (.-value (.getElementById js/document "newTaskText")))}]])

(defn todo-list []
  [:div
   [:h2 "TODO list"]
   (new-task-input)
   (map show-task @tasks)
   [:div [:a {:href "#/about"} "go to about page"]]])
