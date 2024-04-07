package com.github.mauricioaniche.ck.util;

import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {

	private static Set<String> javaKeywords;
	
	// Refatoração (não sei se funciona)
	/*
	javaKeywords = new HashSet<>(Set.of(
            "abstract", "continue", "for", "new", "switch", "assert", "default",
            "goto", "package", "synchronized", "boolean", "do", "if", "private",
            "this", "break", "double", "implements", "protected", "throw", "byte",
            "else", "import", "public", "throws", "case", "enum", "instanceof",
            "return", "transient", "catch", "extends", "int", "short", "try",
            "char", "final", "interface", "static", "void", "class", "finally",
            "long", "strictfp", "volatile", "const", "float", "native", "super",
            "while", "String"
    ));
	*/
	static {
		javaKeywords = new HashSet<String>(){{
			add("abstract");
			add("continue");
			add("for");
			add("new");
			add("switch");
			add("assert***");
			add("default");
			add("goto*");
			add("package");
			add("synchronized");
			add("boolean");
			add("do");
			add("if");
			add("private");
			add("this");
			add("break");
			add("double");
			add("implements");
			add("protected");
			add("throw");
			add("byte");
			add("else");
			add("import");
			add("public");
			add("throws");
			add("case");
			add("enum****");
			add("instanceof");
			add("return");
			add("transient");
			add("catch");
			add("extends");
			add("int");
			add("short");
			add("try");
			add("char");
			add("final");
			add("interface");
			add("static");
			add("void");
			add("class");
			add("finally");
			add("long");
			add("strictfp**");
			add("volatile");
			add("const*");
			add("float");
			add("native");
			add("super");
			add("while");

			add("String");
		}};
	}

	// Refatoração:
	/*

	private static final Set<String> JAVA_KEYWORDS = Set.of("abstract", "assert", "boolean", Adicione outras palavras-chave Java aqui);

    public static Set<String> wordsIn(String fullString) {
        String cleanedString = cleanString(fullString);
        List<String> filteredWords = filterWords(cleanedString);
        return extractWords(filteredWords);
    }

    private static String cleanString(String input) {
        return input.replaceAll("[^\\w]+", " "); // Substituir todos os caracteres não alfanuméricos por um espaço
    }

    private static List<String> filterWords(String input) {
        return Arrays.stream(input.split("\\s+")) // Dividir por espaços em branco
                .filter(word -> !JAVA_KEYWORDS.contains(word.toLowerCase())) // Filtrar palavras-chave Java
                .filter(word -> !word.isEmpty()) // Remover palavras vazias
                .filter(word -> word.matches("\\w*")) // Somente palavras alfanuméricas
                .filter(word -> !word.matches("[0-9]*")) // Excluir palavras compostas apenas por dígitos
                .collect(Collectors.toList());
    }

    private static Set<String> extractWords(List<String> words) {
        Set<String> extractedWords = new HashSet<>();
        for (String word : words) {
            extractedWords.addAll(breakString(word)); // Dividir palavras em partes menores
        }
        return extractedWords;
    }

    private static Set<String> breakString(String word) {
        // Lógica para dividir uma palavra em partes menores, se necessário
        Set<String> parts = new HashSet<>();
        // Implemente a lógica para dividir a palavra aqui, se necessário
        parts.add(word); // Adicione a palavra inteira por padrão
        return parts;
    }

	*/
	public static Set<String> wordsIn(String fullString) {
		String[] cleanString = fullString
				.replace("\t", " ")
				.replace("\n", " ")
				.replace("\r", " ")
				.replace("(", " ")
				.replace(")", " ")
				.replace("{", " ")
				.replace("}", " ")
				.replace("=", " ")
				.replace(">", " ")
				.replace(">", " ")
				.replace("&", " ")
				.replace("|", " ")
				.replace("!", " ")
				.replace("+", " ")
				.replace("*", " ")
				.replace("/", " ")
				.replace("-", " ")
				.replace(";", " ")
				.split(" ");

		List<String> strings = Arrays.stream(cleanString).filter(word -> !javaKeywords.contains(word))
				.filter(word -> !word.isEmpty())
				.filter(word -> word.matches("\\w*"))
				.filter(word -> !word.matches("[0-9]*"))
				.collect(Collectors.toList());

		HashSet<String> words = new HashSet<>();
		for(String string : strings) {
			words.addAll(breakString(string));
		}

		return words;

	}

	// Refatoração:
	/*
	public static List<String> breakString(String string) {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);

            if (isWordSeparator(currentChar)) {
                processWord(currentWord, words);
            } else {
                currentWord.append(currentChar);
            }
        }

        processWord(currentWord, words); // Processa a última palavra, se houver

        return words;
    }

    private static boolean isWordSeparator(char ch) {
        return ch == '_' || Character.isUpperCase(ch);
    }

    private static void processWord(StringBuilder currentWord, List<String> words) {
        if (currentWord.length() > 0) {
            words.add(currentWord.toString());
            currentWord.setLength(0); // Limpa o StringBuilder para a próxima palavra
        }
    }
	*/
	private static Collection<? extends String> breakString(String string) {

		if(string.length() == 1)
			return Arrays.asList(string);

		int current = 0;
		List<String> words = new ArrayList<>();

		for(int i = 1; i < string.length(); i++) {
			if(string.charAt(i) == '_' || Character.isUpperCase(string.charAt(i))) {
				String wordToAdd = string.substring(current, i);
				words.add(wordToAdd);
				current = i + (string.charAt(i) == '_' ? 1 : 0);
			}
		}
		String remainingWord = string.substring(current);
		words.add(remainingWord);
		return words;
	}


	public static String removeSpacesAndIdentation(String toString) {
		return toString
				.trim()
				.replace("\r", " ")
				.replace("\t", " ")
				.replace("\n", " ")
				.replaceAll(" +", " ");
	}

}
