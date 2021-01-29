/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.model;

/**
 *
 * @author user
 */
public class ErrorMessage {
    private String msg = "";

    public ErrorMessage(String msg) {
        this.msg = msg;
    }

    public ErrorMessage() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public void addMessage(String newMsg){
        this.msg = this.msg.concat("\n"+newMsg);
    }

    @Override
    public String toString() {
        return this.msg;
    }
    
}
