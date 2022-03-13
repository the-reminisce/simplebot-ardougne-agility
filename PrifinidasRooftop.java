package me.remie.osrsps.agility.prifinidas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.simplebot.Pathing;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;

@ScriptManifest(author = "Reminisce", category = Category.AGILITY, description = "Does agility course", discord = "Reminisce#1707", 
name = "RPrifinidas Agility", servers = { "OSRSPS", "Zenyte" }, version = "1.0", vip = true)
public class PrifinidasRooftop extends Script {

	public String status;
	public long startTime;
	public int startExperience, startMarks;

	public void onExecute() {
		this.updateStatus("Starting RPrifinidas Agility");
		ctx.getViewport().faceCamera(1, true);
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
				updateStatus("Looting MOG");
				final int cached = ctx.inventory.populate().filter(11849).population(true);
				if (i.click("take")) {
					ctx.onCondition(() -> cached < ctx.inventory.populate().filter(11849).population(true), 250, 12);
				}
			}
			return;
		}

		if (pathing.reachable(new WorldPoint(3250, 6107, 0))) {
			final SimpleObject o = ctx.objects.populate().filter("ladder").filterHasAction("climb").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Climbing ladder");
				if (o.click("climb")) {
					ctx.onCondition(() -> ctx.players.getLocal().getLocation().getPlane() != 0, 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3255, 6109, 2))) {
			final SimpleObject o = ctx.objects.populate().filter("tightrope").filterHasAction("cross").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Crossing tightrope");
				if (o.click("cross")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3272, 6105, 2)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3272, 6105, 2))) {
			final SimpleObject o = ctx.objects.populate().filter("chimney").filterHasAction("jump").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Jumping chimney");
				if (o.click("jump")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3269, 6112, 2)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3269, 6112, 2))) {
			final SimpleObject o = ctx.objects.populate().filter("roof edge").filterHasAction("jump").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Jumping roof edge");
				if (o.click("jump")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3269, 6117, 0)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3269, 6117, 0))) {
			final SimpleObject o = ctx.objects.populate().filter("dark hole").filterHasAction("enter").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Entering dark hole");
				if (o.click("enter")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3294, 6140, 0)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3294, 6140, 0))) {
			final SimpleObject o = ctx.objects.populate().filter("ladder").filterHasAction("climb").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Climbing ladder");
				if (o.click("climb")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3293, 6145, 2)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3293, 6145, 2))) {
			final SimpleObject o = ctx.objects.populate().filter("rope bridge").filterHasAction("cross").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Crossing rope bridge");
				if (o.click("cross")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3281, 6142, 2)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3281, 6142, 2))) {
			final SimpleObject o = ctx.objects.populate().filter("tightrope").filterHasAction("cross").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Crossing tight rope");
				if (o.click("cross")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3271, 6149, 2)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3271, 6149, 2))) {
			final SimpleObject o = ctx.objects.populate().filter("rope bridge").filterHasAction("cross").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Crossing rope bridge");
				if (o.click("cross")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3270, 6158, 2)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3270, 6158, 2))) {
			final SimpleObject o = ctx.objects.populate().filter("tightrope").filterHasAction("cross").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Crossing tight rope");
				if (o.click("cross")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3270, 6158, 2)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3274, 6168, 2))) {
			final SimpleObject o = ctx.objects.populate().filter("tightrope").filterHasAction("cross").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Crossing tight rope");
				if (o.click("cross")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3274, 6168, 2)), 250, 14);
				}
			}
		} else if (pathing.reachable(new WorldPoint(3284, 6177, 0))) {
			final SimpleObject o = ctx.objects.populate().filter("dark hole").filterHasAction("enter").nearest().next();
			if (o != null && o.validateInteractable()) {
				updateStatus("Entering dark hole");
				if (o.click("enter")) {
					ctx.onCondition(() -> !pathing.reachable(new WorldPoint(3284, 6177, 0)), 250, 14);
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
		g.drawString("RPrifinidas Agility                v. " + "0.1", 12, 20);
		g.drawString("Time: " + formatTime(System.currentTimeMillis() - startTime), 14, 42);
		g.drawString("Status: " + status, 14, 56);
		int totalExp = ctx.skills.experience(Skills.AGILITY) - startExperience;
		int expPh = (int) ((totalExp * 3600000D) / (System.currentTimeMillis() - startTime));
		g.drawString("XP: " + totalExp + " (" + expPh + ")", 14, 70);
		int totalMarks = ctx.inventory.populate().filter(11849).population(true) - startMarks;
		int marksPh = (int) ((totalMarks * 3600000D) / (System.currentTimeMillis() - startTime));
		g.drawString("MOG: " + totalMarks + " (" + marksPh + ")", 14, 84);
	}

	public final String formatTime(final long ms) {
		long s = ms / 1000, m = s / 60, h = m / 60;
		s %= 60; m %= 60; h %= 24;
		return String.format("%02d:%02d:%02d", h, m, s);
	}

	public void updateStatus(final String status) {
		ctx.updateStatus(status);
		this.status = status;
	}

	@Override
	public void onChatMessage(final ChatMessage e) {
	}


}
