/**
 * Create a "mine" and set its status (e.g. if
 * it is present or not, if it is revealed or not, and etc...).
 */

package com.example.cmpt276a3.model;

public class Mine {
    private boolean reveal;
    private boolean text_reveal;
    private boolean present;
    private boolean text;

    public Mine(boolean reveal, boolean present, boolean text, boolean text_reveal) {
        this.reveal = reveal;
        this.present = present;
        this.text = text;
        this.text_reveal = text_reveal;
    }

    public boolean isReveal() {
        return reveal;
    }

    public void setReveal(boolean reveal) {
        this.reveal = reveal;
    }

    public boolean isText_reveal() {
        return text_reveal;
    }

    public void setText_reveal(boolean text_reveal) {
        this.text_reveal = text_reveal;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }


}
