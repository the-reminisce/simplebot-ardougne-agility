package me.remie.osrsps.agility.ardougne;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.simplebot.Pathing;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;

import java.awt.*;

@ScriptManifest(author = "Reminisce", category = Category.AGILITY, description = "Does agility course", discord = "Reminisce#1707", 
name = "RArdougne Agility", servers = { "OSRSPS" }, version = "1.0", vip = true)
public class ArdougneRooftop extends Script {

	public String status;
	public long startTime;
	public int startExperience, startMarks;

	public void onExecute() {
		this.status("Starting RArdougne Agility");
		ctx.viewport.faceCamera(1, true);
		this.startExperience = ctx.skills.experience(Skills.AGILITY);
		this.startMarks = ctx.inventory.populate().filter(11849).population(true);
		this.startTime = System.currentTimeMillis();
	}

	public void onProcess() {
		final Pathing pathing = ctx.pathing;

		if (!pathing.running() && pathing.energyLevel() >= 50) {
			pathing.running(true);
			ctx.sleep(200);
		}

		if (!ctx.groundItems.populate().filter(11849).filter((i) -> pathing.reachable(i.getLocation())).isEmpty()) {
			final SimpleGroundItem i = ctx.groundItems.nearest().next();
			if (i != null && i.validateInteractable()) {
				status("Looting MOG");
				final int cached = ctx.inventory.populate().filter(11849).population(true);
				if (i.click("take")) {
					ctx.onCondition(() -> cached < ctx.inventory.populate().filter(11849).population(true), 250, 12);
				}
			}
			return;
		}

		if (pathing.reachable(new WorldPoint(2673, 3297, 0))) {
			final SimpleObject o = ctx.objects.populate().filter("wooden beams").filterHasAction("climb-up").nearest().next();
			if (o != null && o.validateInteractable()) {
				status("Climbing Wooden beams");
				if (o.click("climb-up")) {
					ctx.onCondition(() -> ctx.players.getLocal().getLocation().getPlane() != 0, 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(2671, 3300, 3))) {
			final SimpleObject o = ctx.objects.populate().filter("gap").filterHasAction("jump").nearest().next();
			if (o != null && o.validateInteractable()) {
				status("Jumping gap");
				if (o.click("jump")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(2671, 3300, 3)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(2664, 3318, 3))) {
			final SimpleObject o = ctx.objects.populate().filter("plank").filterHasAction("walk-on").nearest().next();
			if (o != null && o.validateInteractable()) {
				status("Crossing plank");
				if (o.click("walk-on")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(2664, 3318, 3)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(2655, 3318, 3))) {
			final SimpleObject o = ctx.objects.populate().filter("gap").filterHasAction("jump").nearest().next();
			if (o != null && o.validateInteractable()) {
				status("Jumping gap 2");
				if (o.click("jump")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(2655, 3318, 3)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(2653, 3313, 3))) {
			final SimpleObject o = ctx.objects.populate().filter("gap").filterHasAction("jump")
					.filter((i) -> i.getLocation().distanceTo(new WorldPoint(2653, 3311, 3)) <= 2).nearest().next();
			if (o != null && o.validateInteractable()) {
				status("Jumping gap 3");
				if (o.click("jump")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(2653, 3313, 3)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(2651, 3308, 3))) {
			final SimpleObject o = ctx.objects.populate().filter("steep roof").filterHasAction("balance-across").nearest().next();
			if (o != null && o.validateInteractable()) {
				status("Balancing across roof");
				if (o.click("balance-across")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(2651, 3308, 3)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(2656, 3297, 3))) {
			final SimpleObject o = ctx.objects.populate().filter("gap").filterHasAction("jump").nearest().next();
			if (o != null && o.validateInteractable()) {
				status("Jumping gap 4");
				if (o.click("jump")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(2656, 3297, 3)), 250, 14);
				}
			}
		}
	}

	@Override
	public void onTerminate() {
	}

	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setColor(Color.BLACK);
		g.fillRect(5, 2, 192, 86);
		g.setColor(Color.decode("#D93B26"));
		g.drawRect(5, 2, 192, 86);
		g.drawLine(8, 24, 194, 24);

		g.setColor(Color.decode("#1C6497"));
		g.drawString("RArdougne Agility                v. " + "0.1", 12, 20);
		g.drawString("Time: " + ctx.paint.formatTime(System.currentTimeMillis() - startTime), 14, 42);
		g.drawString("Status: " + status, 14, 56);
		int totalExp = ctx.skills.experience(Skills.AGILITY) - startExperience;
		g.drawString("XP: " + ctx.paint.formatValue(totalExp) + " (" + ctx.paint.valuePerHour(totalExp, startTime) + ")", 14, 70);
		int totalMarks = ctx.inventory.populate().filter(11849).population(true) - startMarks;
		g.drawString("MOG: " + ctx.paint.formatValue(totalMarks) + " (" + ctx.paint.valuePerHour(totalMarks, startTime) + ")", 14, 84);
	}

	public void status(final String status) {
		this.status = status;
	}

	@Override
	public void onChatMessage(final ChatMessage e) {
	}


}
