(ns todo-list.views.main
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce tasks (atom []))

(defn- toggle-status [status]
  (if (= status :done) :notdone :done))

(defn- toggle-task [index]
  (swap! tasks #(update-in @tasks [index :status] toggle-status)))

(defn- add-task [text]
  (swap! tasks #(conj % {:text text, :status :notdone})))

(defn- show-task [index task]
  "Show a single task"
  ^{:key index} [:div {:class (str "task task-" (name (:status task)))
                      :on-click #(toggle-task index)}
                [:b (:text task)]])

(defn- new-task-input []
  "The input box for adding tasks"
  [:div
   [:input {:type "text" :id "newTaskText"}]
   [:input {:type "button" :value "Add task"
            :on-click #(add-task (.-value (.getElementById js/document "newTaskText")))}]])

(defn- task-count []
  "Shows the task metrics"
  (let [total-tasks (count @tasks)
        completed-tasks (count (filter #(= :done (:status %)) @tasks))]
    [:div
     [:p (str "Tasks completed " completed-tasks "/" total-tasks)]]))

(defn todo-list []
  [:div
   [:h2 "TODO list"]
   (new-task-input)
   (map-indexed show-task @tasks)
   (task-count)
   [:div [:a {:href "#/about"} "go to about page"]]])
