package common.validation.validator.notion;

import common.validation.ValidationMessage;
import common.validation.dto.InvalidResponse;
import common.validation.dto.NotionRequest;
import common.validation.validator.NotionValidator;

import java.util.Collections;
import java.util.List;

public class ContentValidator implements NotionValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("content", ValidationMessage.CONTENT);
    private static final int MAX_LENGTH = 1_000;

    @Override
    public List<InvalidResponse> validate(NotionRequest request) {
        String content = request.getContent();

        if (content == null || isBlank(content)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(content)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        return Collections.emptyList();
    }

    private boolean isBlank(String title) {
        return title.trim().isEmpty();
    }

    private boolean isLength(String title) {
        return MAX_LENGTH < title.trim().length();
    }
}
