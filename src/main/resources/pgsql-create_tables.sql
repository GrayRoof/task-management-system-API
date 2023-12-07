CREATE TABLE "users"(
    "id" BIGINT NOT NULL,
    "Name" VARCHAR(255) NOT NULL,
    "LastName" VARCHAR(255) NOT NULL,
    "MiddleName" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "users" ADD PRIMARY KEY("id");
ALTER TABLE
    "users" ADD CONSTRAINT "users_email_unique" UNIQUE("email");
CREATE TABLE "task_states"(
    "id" BIGINT NOT NULL,
    "Name" BIGINT NOT NULL
);
ALTER TABLE
    "task_states" ADD PRIMARY KEY("id");
CREATE TABLE "task_priority"(
    "id" BIGINT NOT NULL,
    "Name" BIGINT NOT NULL
);
ALTER TABLE
    "task_priority" ADD PRIMARY KEY("id");
CREATE TABLE "tasks"(
    "id" BIGINT NOT NULL,
    "Name" VARCHAR(255) NOT NULL,
    "Description" VARCHAR(255) NULL,
    "Performer" BIGINT NOT NULL,
    "Author" BIGINT NOT NULL,
    "State" BIGINT NOT NULL,
    "Priority" BIGINT NOT NULL
);
ALTER TABLE
    "tasks" ADD PRIMARY KEY("id");
CREATE TABLE "task_comments"(
    "task_id" BIGINT NOT NULL,
    "comment_id" BIGINT NOT NULL
);
CREATE TABLE "comments"(
    "id" BIGINT NOT NULL,
    "Text" VARCHAR(255) NOT NULL,
    "Author" BIGINT NOT NULL
);
ALTER TABLE
    "comments" ADD PRIMARY KEY("id");
ALTER TABLE
    "task_comments" ADD CONSTRAINT "task_comments_comment_id_foreign" FOREIGN KEY("comment_id") REFERENCES "comments"("id");
ALTER TABLE
    "tasks" ADD CONSTRAINT "tasks_author_foreign" FOREIGN KEY("Author") REFERENCES "users"("id");
ALTER TABLE
    "tasks" ADD CONSTRAINT "tasks_priority_foreign" FOREIGN KEY("Priority") REFERENCES "task_priority"("id");
ALTER TABLE
    "tasks" ADD CONSTRAINT "tasks_performer_foreign" FOREIGN KEY("Performer") REFERENCES "users"("id");
ALTER TABLE
    "task_comments" ADD CONSTRAINT "task_comments_task_id_foreign" FOREIGN KEY("task_id") REFERENCES "tasks"("id");
ALTER TABLE
    "comments" ADD CONSTRAINT "comments_author_foreign" FOREIGN KEY("Author") REFERENCES "users"("id");
ALTER TABLE
    "tasks" ADD CONSTRAINT "tasks_state_foreign" FOREIGN KEY("State") REFERENCES "task_states"("id");