package com.example.demo.core.context;

import com.example.demo.core.security.user.UserDetailsImpl;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Setter
@Service
public class GlobalUserContextService {

    /**
     * -- SETTER --
     * Explicitly set the current user ID for this request/context
     */
    private UUID currentUserId;

    /**
     * Gets the current user's ID from Spring Security context or from explicitly set value
     *
     * @return The user ID as a string
     */
    public UUID getUserId() {
        // First check if a user ID has been explicitly set
        if (currentUserId != null) {
            return currentUserId;
        }

        // Otherwise get from Spring Security context
        return getCurrentUserIdFromSecurity()
                .orElse(null);
    }

    /**
     * Extract user ID from Spring Security context
     *
     * @return Optional containing the UUID if present
     */
    private Optional<UUID> getCurrentUserIdFromSecurity() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null
                && authentication.isAuthenticated()
                && authentication.getDetails() instanceof UserDetailsImpl userDetails) {
            return Optional.ofNullable(userDetails.getId());
        }

        return Optional.empty();
    }
}
