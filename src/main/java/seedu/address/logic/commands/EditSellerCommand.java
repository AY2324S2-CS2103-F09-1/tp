package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.State;
import seedu.address.model.house.House;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Seller;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditSellerCommand extends Command {

    public static final String COMMAND_WORD = "editSeller";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the seller identified "
            + "by the index number used in the displayed list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_SELLER_SUCCESS = "Edited Person(Seller): %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_WRONG_TYPE = "The person you are trying to edit is not a seller.";

    private final Index index;
    private final EditSellerDescriptor editSellerDescriptor;

    /**
     * @param index                of the seller in the filtered person list to edit
     * @param editSellerDescriptor details to edit the seller with
     */
    public EditSellerCommand(Index index, EditSellerDescriptor editSellerDescriptor) {
        requireNonNull(index);
        requireNonNull(editSellerDescriptor);

        this.index = index;
        this.editSellerDescriptor = new EditSellerDescriptor(editSellerDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!(lastShownList.get(index.getZeroBased()) instanceof Seller)) {
            throw new CommandException(MESSAGE_WRONG_TYPE);
        }

        Seller sellerToEdit = (Seller) lastShownList.get(index.getZeroBased());
        Seller editedSeller = createEditedSeller(sellerToEdit, editSellerDescriptor);

        if (!sellerToEdit.isSamePerson(editedSeller) && model.hasPerson(editedSeller)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(sellerToEdit, editedSeller);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.setState(State.PERSON_LIST);
        return new CommandResult(String.format(MESSAGE_EDIT_SELLER_SUCCESS, Messages.format(editedSeller)));
    }

    /**
     * Creates and returns a {@code Seller} with the details of {@code sellerToEdit}
     * edited with {@code editSellerDescriptor}.
     */
    private static Seller createEditedSeller(Seller sellerToEdit, EditSellerDescriptor editSellerDescriptor) {
        assert sellerToEdit != null;

        Name updatedName = editSellerDescriptor.getName().orElse(sellerToEdit.getName());
        Phone updatedPhone = editSellerDescriptor.getPhone().orElse(sellerToEdit.getPhone());
        Email updatedEmail = editSellerDescriptor.getEmail().orElse(sellerToEdit.getEmail());

        // Houses are non-editable via editSellerCommand
        ObservableList<House> houses = sellerToEdit.getHouses();

        return new Seller(updatedName, updatedPhone, updatedEmail, houses);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSellerCommand)) {
            return false;
        }

        EditSellerCommand otherEditSellerCommand = (EditSellerCommand) other;
        return index.equals(otherEditSellerCommand.index)
                && editSellerDescriptor.equals(otherEditSellerCommand.editSellerDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editSellerDescriptor", editSellerDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the seller with. Each non-empty field value will replace the
     * corresponding field value of the Seller.
     */
    public static class EditSellerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Tag> tags;

        public EditSellerDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSellerDescriptor(EditSellerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
        }


        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSellerDescriptor)) {
                return false;
            }

            EditSellerDescriptor otherEditSellerDescriptor = (EditSellerDescriptor) other;
            return Objects.equals(name, otherEditSellerDescriptor.name)
                    && Objects.equals(phone, otherEditSellerDescriptor.phone)
                    && Objects.equals(email, otherEditSellerDescriptor.email)
                    && Objects.equals(tags, otherEditSellerDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("tags", tags)
                    .toString();
        }
    }
}
