(ns todo-list.views.main)

(defonce tasks (atom []))

(defn- add-task [text]
  (swap! tasks #(conj % {:text text, :status :notdone})))

(add-task "One task")
(add-task "Other task")

(defn- show-task [task]
  "Show a single task"
  [:div {:class (str "task task-" (name (:status task)))}
   [:b (:text task)]])

(defn todo-list []
  [:div
   [:h2 "TODO list"]
   (map show-task @tasks)
   [:div [:a {:href "#/about"} "go to about page"]]])
