package io.github.user_activity_cli.cli;

import io.github.user_activity_cli.core.Event;
import io.github.user_activity_cli.core.GithubClient;
import io.github.user_activity_cli.core.Repo;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AplicationCli {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("github-> ");
                String line = sc.nextLine();
                String[] input = line.contains(" ") ? line.trim().split("\\s+") : new String[]{line};
                validateInput(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void validateInput(String[] input) {
        if (input.length < 1 || input[0].isBlank()) {
            System.out.println("Error: No se aceptan comandos incompletos o vacíos.");
        } else if (input.length > 2) {
            System.out.println("Error: El comando recibe más parametros de los solicitados.");
        } else {
            String command = input[0];
            String username = input.length == 2 ? input[1] : "";
            executeCommand(command, username);
        }
    }

    private static void executeCommand(String command, String username) {
        switch (command) {
            case "github-activity" -> {
                if (username.isBlank()) System.out.println("Error: el usuario no debe estar en blanco.");
                else getActivityClient(username);
            }
            case "exit" -> System.exit(0);
            default -> System.out.println("Error: El comando no existe.");
        }
    }

    private static void getLogActivityClient(String username) {
        GithubClient client = new GithubClient();
        StringBuilder sb = new StringBuilder("Eventos: \n");
        AtomicInteger totalEventos = new AtomicInteger();
        client.getEvents(username).forEach(e -> {
            sb.append("Tipo: ").append(e.getType())
                    .append(", Creado en: ").append(e.getCreatedAt())
                    .append(", Repo: ").append(e.getRepo().getName()).append("\n");
            totalEventos.getAndIncrement();
        });
        sb.append("Total de eventos: ").append(totalEventos);
        System.out.println(sb);
    }

    private static void getActivityClient(String username) {
        GithubClient client = new GithubClient();
        StringBuilder sb = new StringBuilder("Resumen: \n");
        List<Event> events = client.getEvents(username);
        Map<Repo, Map<String, Long>> eventCountsByRepoAndType = events.stream()
                .collect(Collectors.groupingBy(
                        Event::getRepo, Collectors.groupingBy(Event::getType, Collectors.counting())
                ));
        for (Map.Entry<Repo, Map<String, Long>> repoEntry : eventCountsByRepoAndType.entrySet()) {
            Repo repo = repoEntry.getKey();
            sb.append("Repositorio: ").append(repo.getName()).append("\n");
            for (Map.Entry<String, Long> typeEntry : repoEntry.getValue().entrySet()) {
                String type = typeEntry.getKey();
                Long count = typeEntry.getValue();
                sb.append("Evento: ").append(type).append(", Total: ").append(count).append("\n");
            }
           sb.append("-------------------------------------------------------------\n");
        }
        System.out.println(sb);
    }
}
