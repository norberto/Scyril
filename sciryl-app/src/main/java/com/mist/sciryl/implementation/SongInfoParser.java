package com.mist.sciryl.implementation;

import com.mist.sciryl.app.helpers.Helpers;
import com.mist.sciryl.app.helpers.Pipe;
import com.mist.sciryl.app.helpers.PipeBuilder;

public class SongInfoParser {

    public static final String SEPARATOR_AUTHOR_FEAT = "feat";
    public static final ReplacerPipe SEPARATOR_AUTHOR_TITLE = new ReplacerPipe("00", "-");
    public static final ReplacerPipe REPR_SPACE = new ReplacerPipe("_", " ");
    public static final ReplacerPipe REPR_LEFT_BRACES = new ReplacerPipe("01", "(");
    public static final ReplacerPipe REPR_RIGHT_BRACES = new ReplacerPipe("02", ")");
    public static final ReplacerPipe REPR_UPPER_COMMA = new ReplacerPipe("03", "'");
    public static final ReplacerPipe REPR_QUESTION_MARK = new ReplacerPipe("04", "?");
    public static final ReplacerPipe REPR_PERIOD = new ReplacerPipe("05", ".");

    public static final Pipe UPPERCASE_NEXT_LETTER = new Pipe() {
        private static final String REGEX = "99";
        @Override
        public Object pipe(Object obj) {
            String name = (String) obj;
            if (name.contains(REGEX)) {
                StringBuilder sb = new StringBuilder();
                String[] parts = name.split(REGEX);

                sb.append(parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    String part = parts[i];
                    sb.append(Character.toUpperCase(part.charAt(0)));
                    sb.append(part.substring(1));
                }

                return sb.toString();
            }
            return name;
        }
    };

    public static class ReplacerPipe implements Pipe {
        private String regex;
        private String replacement;

        public ReplacerPipe(String regex, String replacement) {
            this.regex = regex;
            this.replacement = replacement;
        }

        public Object pipe(Object value) {
            return ((String) value).replaceAll(regex, replacement);
        }
    }

    private SongInfoParser() {
    }

    public static String normalizeName(String name) {
        String result = PipeBuilder.piperize(name,
            REPR_SPACE,
            SEPARATOR_AUTHOR_TITLE,
            REPR_LEFT_BRACES,
            REPR_RIGHT_BRACES,
            REPR_UPPER_COMMA,
            REPR_QUESTION_MARK,
            UPPERCASE_NEXT_LETTER,
            REPR_PERIOD
        );

        return Helpers.capitalizeString(result);
    }

}
