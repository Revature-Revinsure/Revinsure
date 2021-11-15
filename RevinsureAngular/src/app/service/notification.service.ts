import { Injectable } from '@angular/core';
import { Message } from '../models/message';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  
  message: Message = {message: "No Messages"};
  showMessage: boolean = false;

  constructor() { }

  sendMessage(message: string | undefined){
    // let newMessage: Message = {message: message};

    this.message = {message: message};
    this.showMessage = true;

  }

  dismiss(){
    this.showMessage = false;
  }
}
