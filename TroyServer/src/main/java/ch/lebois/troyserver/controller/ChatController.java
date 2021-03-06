package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Message;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.model.ChatModel;
import ch.lebois.troyserver.model.MessageModel;
import ch.lebois.troyserver.service.CookieService;
import ch.lebois.troyserver.service.ModelService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Felix
 * @date 16.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.controller
 **/

@Controller
@RequestMapping(value = {"chat/{client}"})
public class ChatController {

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    private CookieService cookieService;
    private ModelService modelService;

    public ChatController(ClientRepository clientRepository, MessageRepository messageRepository,
                          CookieService cookieService, ModelService modelService) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.cookieService = cookieService;
        this.modelService = modelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getChat(@PathVariable(name = "client") String client, Model model, HttpServletRequest request,
                          ChatModel chatModel) {
        try {
            model.addAttribute("user", cookieService.getCurrentUser(request));
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

        model.addAttribute("client", modelService.getClientModel(clientRepository.findOne(client)));

        for (Message message : messageRepository.findMessagesByTypeAndPcNameFk("message", client)) {
            String[] split = message.getText().split(": ");
            MessageModel messageModel = new MessageModel();
            messageModel.setClient(split[0]);
            messageModel.setText(split[1]);
            chatModel.getMessages().add(messageModel);
        }

        model.addAttribute("messages", chatModel);

        return "chat";

    }
}
