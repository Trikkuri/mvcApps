package mvc;

import java.util.ArrayList;
import java.util.List;

public abstract class Model {
    private boolean unsavedChanges = false;
    private String fileName = null;
    private List<Subscriber> subscribers;

    public Model() {
        subscribers = new ArrayList<>();
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean unsavedChanges) {
        this.unsavedChanges = unsavedChanges;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }
}
