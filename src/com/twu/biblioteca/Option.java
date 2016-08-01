package com.twu.biblioteca;

public class Option {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Option(int id, String optionName) {
        this.id = id;
        this.name = optionName;
    }

    public void execute(BibliotecaApp app) {
        if(this.name.equals("List Books")) {
            app.displayBookListInfo();
        } else if(this.name.equals("Quit")) {
            app.exit();
        } else if(this.name.equals("Checkout Book")) {
            app.checkoutBook();
        } else if(this.name.equals("Return Book")) {
            app.returnBook();
        }
    }

    public String getOptionInfo() {
        return this.id + ". " + this.name;
    }
}
