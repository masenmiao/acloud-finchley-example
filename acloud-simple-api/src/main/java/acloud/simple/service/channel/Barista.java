package acloud.simple.service.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Barista {//定义一个有输入，有输出，
	//通道类型和通道名称的channel参数

    String INPUT_CHANNEL = "input_channel";
    String OUTPUT = "output_channel";  //topic name
  
//    String INPUT1 = "input1";  
//    String OUTPUT1 = "output1";  
    
    @Input(Barista.INPUT_CHANNEL)
    SubscribableChannel logInput();  
    
    @Output(Barista.OUTPUT)
    MessageChannel logOutPut();  
  
      
}  

