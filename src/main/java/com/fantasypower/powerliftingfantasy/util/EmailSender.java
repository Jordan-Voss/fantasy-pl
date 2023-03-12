package com.fantasypower.powerliftingfantasy.util;

import jakarta.mail.MessagingException;

public interface EmailSender {
    void send(String to, String email) throws MessagingException;
}
