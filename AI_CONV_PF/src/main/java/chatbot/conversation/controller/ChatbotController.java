/**
 * This is automatically generated by sprout plug-in.
 */
package chatbot.conversation.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import chatbot.conversation.service.ChatbotService;
import chatbot.platform.util.ConvModelWrapper;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 
 * @since 
 * @author Administrator
 */
@Controller
@RequestMapping("/")
public class ChatbotController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ChatbotService chatbotService;
	
	@RequestMapping({"/", ""})
    public String init(UserAgent userAgent, Device device, Model model) {
		return "redirect:/getChatbotPage.do";
    }
	
	@RequestMapping("/getChatbotPage")
    public String getChatbotPage(Map<String, String> map, Model model) {        
    	if(logger.isInfoEnabled()){
    		logger.info("★★★★★★★★★★★★ In getChatbotPage controller.....");
    	}    	
    	
        return "chatbot/chatbot";
    }
	
	@RequestMapping("/sendText")
	public Model sendText(@RequestBody Map<String, String> map, Model model) throws Exception {      
    	if(logger.isInfoEnabled()){
    		logger.info("★★★★★★★★★★★★ In sendText controller..... : " + map);
    	}
    	ConvModelWrapper wrapper = new ConvModelWrapper(map.get("text"), map.get("context"));
    	model.addAttribute("returnMessage", chatbotService.sendText(wrapper).getConversationModel());
    	
        return model;
    } 
}