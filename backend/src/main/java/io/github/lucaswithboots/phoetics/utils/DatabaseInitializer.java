package io.github.lucaswithboots.phoetics.utils;

import io.github.lucaswithboots.phoetics.model.Phonetic;
import io.github.lucaswithboots.phoetics.service.PhoneticService;
import mtfn.MetaphonePtBr;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initializeDatabase(PhoneticService phoneticService) {
        return args -> {
            // Verifica se o banco está vazio
            if (phoneticService.count() == 0) {
                List<String> names = Arrays.asList(
                        "João Silva", "Maria Souza", "Carlos Oliveira", "Ana Santos", "Pedro Costa",
                        "Lucia Ferreira", "Paulo Rodrigues", "Mariana Alves", "Fernando Pereira", "Camila Lima",
                        "Ricardo Nunes", "Patrícia Gomes", "Eduardo Martins", "Juliana Castro", "Marcos Ribeiro",
                        "Amanda Dias", "Rodrigo Carvalho", "Isabela Monteiro", "Gustavo Henrique", "Beatriz Ramos",
                        "Lucas Mendes", "Vanessa Correia", "Roberto Andrade", "Tatiane Teixeira", "Felipe Cunha",
                        "Daniela Peixoto", "Alexandre Moreira", "Gabriela Fonseca", "Rafael Pires", "Letícia Duarte",
                        "Antônio Barbosa", "Cláudia Rocha", "Sérgio Cardoso", "Renata Caldeira", "Vinícius Tavares",
                        "Natália Macedo", "Diego Figueiredo", "Priscila Vasconcelos", "Leonardo Miranda", "Aline Barros",
                        "Thiago Nascimento", "Laura Bezerra", "Bruno Medeiros", "Yasmin Guimarães", "André Santana",
                        "Carolina Dantas", "César Freitas", "Jéssica Brito", "Marcelo Queiroz", "Raquel Siqueira",
                        "José Augusto", "Fernanda Lopes", "Márcio Xavier", "Tainá Cordeiro", "Otávio Rezende",
                        "Sabrina Moura", "Gilberto Sarmento", "Adriana Falcão", "Hugo Abreu", "Elaine Pinheiro",
                        "Igor Fontes", "Simone Barcelos", "Arthur Maia", "Larissa Paiva", "Kleber Torres",
                        "Viviane Azevedo", "Francisco Neto", "Helena Camargo", "Dênis Couto", "Mônica Valença",
                        "Wagner Diniz", "Alice Pimentel", "Leandro Rosa", "Cristina Bento", "Matheus Soares",
                        "Luana Aguiar", "Fábio Galvão", "Sueli Marques", "Erick Leal", "Lívia Franco",
                        "Jorge Melo", "Regina Bastos", "Caio Goulart", "Bianca Neves", "Rogério Campos",
                        "Cecília Vidal", "Samuel Quintana", "Danielly Costa", "Mauro Faria", "Teresa Novaes",
                        "Anderson Sampaio", "Vera Luz", "Edson Paranhos", "Olivia Corte Real", "Nelson Salgado",
                        "Miriam Ferraz", "Davi Lemos", "Estela Góis", "Raul Ventura", "Clara Meireles",
                        "Ivan Serpa", "Lorena Padilha", "Geraldo Teles", "Rosana Câmara", "Tomás Albuquerque",
                        "Noemi Ilha", "Humberto Maciel", "Angelina Caiado", "Saulo Dorneles", "Lúcia Varela",

                        // Nomes compostos
                        "João Pedro Almeida", "Maria Eduarda Lima", "Carlos Augusto Santos", "Ana Clara Rodrigues",
                        "Pedro Henrique Costa", "Luiza Fernanda Souza", "Paulo Sérgio Oliveira", "Mariana Isabel Alves",
                        "Fernando José Pereira", "Camila Luiza Mendes",

                        // Nomes comuns repetidos com sobrenomes diferentes
                        "João Oliveira", "João Pereira", "João Costa", "João Alves",
                        "Maria Silva", "Maria Oliveira", "Maria Pereira", "Maria Costa",
                        "José Santos", "José Souza", "José Ferreira", "José Rodrigues",

                        // Sobrenomes compostos
                        "Ricardo Silva Santos", "Patrícia Souza Oliveira", "Eduardo Costa Pereira", "Juliana Alves Ferreira",
                        "Marcos Rodrigues Silva", "Amanda Pereira Costa", "Rodrigo Almeida Souza", "Isabela Costa Rodrigues",

                        // Nomes com prefixos/sufixos
                        "Dr. Carlos Mendes", "Dra. Ana Lúcia Fontes", "Sr. Paulo Roberto", "Sra. Maria das Graças",
                        "Prof. Fernando Teixeira", "Profª. Claudia Menezes"
                );

                // Para cada nome, cria e salva um registro fonético
                for (String name : names) {
                    Phonetic phonetic = new Phonetic();
                    String code = new MetaphonePtBr(name).toString().replaceAll("\\s+", "");

                    phonetic.setName(name);
                    phonetic.setCode(code);

                    phoneticService.save(phonetic);
                }

                System.out.println("Banco de dados populado com " + names.size() + " registros fictícios.");
            }
        };
    }
}