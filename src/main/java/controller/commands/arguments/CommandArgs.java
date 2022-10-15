package controller.commands.arguments;

import parser.ConfigParser;

import java.net.http.HttpClient;

public record CommandArgs(ConfigParser parser, HttpClient client, String params) {
}
