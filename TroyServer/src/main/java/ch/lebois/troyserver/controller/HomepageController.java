package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.model.ClientModel;
import ch.lebois.troyserver.service.CookieService;
import ch.lebois.troyserver.service.ModelService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project: Hermann
 **/

@Controller
@RequestMapping("dashboard")
public class HomepageController {

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    private ModelService modelService;
    private CookieService cookieService;

    public HomepageController(CookieService cookieService, ClientRepository clientRepository,
                              MessageRepository messageRepository, ModelService modelService) {
        this.cookieService = cookieService;
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.modelService = modelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomepage(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("user", cookieService.getCurrentUser(request));
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

        List<ClientModel> clients = new ArrayList<>();

        for (Client client : clientRepository.findAll()) {
            clients.add(modelService.getClientModel(client));
        }
        model.addAttribute("model", clients);
        model.addAttribute("registrations", messageRepository.findMessagesByType("newUser"));
        return "homepage";

    }
}
