import java.util.Objects;

public class Task implements Comparable<Task> {
	private String content;

	private boolean completed;

	public Task(String content) {
		this.content = content;
		completed = false;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return (completed ? "[x] " : "[ ] ") + content + "";
	}

	@Override
	public int compareTo(Task o) {
		if (this.completed == o.completed)
			return this.content.compareTo(o.content);
		else if (this.completed) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return compareTo(task) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, completed);
	}
}
