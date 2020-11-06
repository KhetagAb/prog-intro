__current-repo/java-solutions/markup/Paragraph.java:3: error: package markup.interfeces does not exist
import markup.interfeces.BBCodeAble;
                        ^
__current-repo/java-solutions/markup/Paragraph.java:4: error: package markup.interfeces does not exist
import markup.interfeces.ListAble;
                        ^
__current-repo/java-solutions/markup/Paragraph.java:5: error: package markup.interfeces does not exist
import markup.interfeces.MarkdownAble;
                        ^
__current-repo/java-solutions/markup/Paragraph.java:6: error: package markup.interfeces does not exist
import markup.interfeces.ParagraphAble;
                        ^
__current-repo/java-solutions/markup/AbstractElement.java:3: error: package markup.interfeces does not exist
import markup.interfeces.ParagraphAble;
                        ^
__current-repo/java-solutions/markup/Paragraph.java:10: error: cannot find symbol
public class Paragraph extends AbstractElement implements ListAble, MarkdownAble, BBCodeAble {
                                                          ^
  symbol: class ListAble
__current-repo/java-solutions/markup/Paragraph.java:10: error: cannot find symbol
public class Paragraph extends AbstractElement implements ListAble, MarkdownAble, BBCodeAble {
                                                                    ^
  symbol: class MarkdownAble
__current-repo/java-solutions/markup/Paragraph.java:10: error: cannot find symbol
public class Paragraph extends AbstractElement implements ListAble, MarkdownAble, BBCodeAble {
                                                                                  ^
  symbol: class BBCodeAble
__current-repo/java-solutions/markup/UnorderedList.java:3: error: package markup.interfeces does not exist
import markup.interfeces.ListAble;
                        ^
__current-repo/java-solutions/markup/AbstractList.java:3: error: package markup.interfeces does not exist
import markup.interfeces.BBCodeAble;
                        ^
__current-repo/java-solutions/markup/AbstractList.java:4: error: package markup.interfeces does not exist
import markup.interfeces.ListAble;
                        ^
__current-repo/java-solutions/markup/AbstractList.java:8: error: cannot find symbol
public abstract class AbstractList implements ListAble {
                                              ^
  symbol: class ListAble
__current-repo/java-solutions/markup/ListItem.java:3: error: package markup.interfeces does not exist
import markup.interfeces.BBCodeAble;
                        ^
__current-repo/java-solutions/markup/ListItem.java:4: error: package markup.interfeces does not exist
import markup.interfeces.ListAble;
                        ^
__current-repo/java-solutions/markup/ListItem.java:8: error: cannot find symbol
public class ListItem implements BBCodeAble {
                                 ^
  symbol: class BBCodeAble
__current-repo/java-solutions/markup/ListItem.java:9: error: cannot find symbol
    private final List<ListAble> elements;
                       ^
  symbol:   class ListAble
  location: class ListItem
__current-repo/java-solutions/markup/ListItem.java:11: error: cannot find symbol
    public ListItem(List<ListAble> elements) {
                         ^
  symbol:   class ListAble
  location: class ListItem
__current-repo/java-solutions/markup/Paragraph.java:11: error: cannot find symbol
    public Paragraph(List<ParagraphAble> elements) {
                          ^
  symbol:   class ParagraphAble
  location: class Paragraph
__current-repo/java-solutions/markup/AbstractElement.java:8: error: cannot find symbol
    protected final List<ParagraphAble> elements;
                         ^
  symbol:   class ParagraphAble
  location: class AbstractElement
__current-repo/java-solutions/markup/AbstractElement.java:10: error: cannot find symbol
    protected AbstractElement(List<ParagraphAble> elements) {
                                   ^
  symbol:   class ParagraphAble
  location: class AbstractElement
__current-repo/java-solutions/markup/Emphasis.java:3: error: package markup.interfeces does not exist
import markup.interfeces.BBCodeAble;
                        ^
__current-repo/java-solutions/markup/Emphasis.java:4: error: package markup.interfeces does not exist
import markup.interfeces.MarkdownAble;
                        ^
__current-repo/java-solutions/markup/Emphasis.java:5: error: package markup.interfeces does not exist
import markup.interfeces.ParagraphAble;
                        ^
__current-repo/java-solutions/markup/Emphasis.java:9: error: cannot find symbol
public class Emphasis extends AbstractElement implements ParagraphAble {
                                                         ^
  symbol: class ParagraphAble
__current-repo/java-solutions/markup/Emphasis.java:10: error: cannot find symbol
    public Emphasis(List<ParagraphAble> elements) {
                         ^
  symbol:   class ParagraphAble
  location: class Emphasis
__current-repo/java-solutions/markup/Strikeout.java:3: error: package markup.interfeces does not exist
import markup.interfeces.BBCodeAble;
                        ^
__current-repo/java-solutions/markup/Strikeout.java:4: error: package markup.interfeces does not exist
import markup.interfeces.MarkdownAble;
                        ^
__current-repo/java-solutions/markup/Strikeout.java:5: error: package markup.interfeces does not exist
import markup.interfeces.ParagraphAble;
                        ^
__current-repo/java-solutions/markup/Strikeout.java:9: error: cannot find symbol
public class Strikeout extends AbstractElement implements ParagraphAble {
                                                          ^
  symbol: class ParagraphAble
__current-repo/java-solutions/markup/Strikeout.java:10: error: cannot find symbol
    public Strikeout(List<ParagraphAble> elements) {
                          ^
  symbol:   class ParagraphAble
  location: class Strikeout
__current-repo/java-solutions/markup/Text.java:3: error: package markup.interfeces does not exist
import markup.interfeces.ParagraphAble;
                        ^
__current-repo/java-solutions/markup/Text.java:5: error: cannot find symbol
public class Text implements ParagraphAble {
                             ^
  symbol: class ParagraphAble
__current-repo/java-solutions/markup/Strong.java:3: error: package markup.interfeces does not exist
import markup.interfeces.BBCodeAble;
                        ^
__current-repo/java-solutions/markup/Strong.java:4: error: package markup.interfeces does not exist
import markup.interfeces.MarkdownAble;
                        ^
__current-repo/java-solutions/markup/Strong.java:5: error: package markup.interfeces does not exist
import markup.interfeces.ParagraphAble;
                        ^
__current-repo/java-solutions/markup/Strong.java:9: error: cannot find symbol
public class Strong extends AbstractElement implements ParagraphAble {
                                                       ^
  symbol: class ParagraphAble
__current-repo/java-solutions/markup/Strong.java:10: error: cannot find symbol
    public Strong(List<ParagraphAble> elements) {
                       ^
  symbol:   class ParagraphAble
  location: class Strong
__current-repo/java-solutions/markup/AbstractElement.java:16: error: cannot find symbol
        for (ParagraphAble e: elements) {
             ^
  symbol:   class ParagraphAble
  location: class AbstractElement
__current-repo/java-solutions/markup/AbstractElement.java:24: error: cannot find symbol
        for (ParagraphAble e: elements) {
             ^
  symbol:   class ParagraphAble
  location: class AbstractElement
__current-repo/java-solutions/markup/Paragraph.java:15: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Paragraph.java:17: error: cannot find symbol
        for (ParagraphAble e: elements) {
             ^
  symbol:   class ParagraphAble
  location: class Paragraph
__current-repo/java-solutions/markup/Paragraph.java:22: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Paragraph.java:24: error: cannot find symbol
        for (ParagraphAble e: elements) {
             ^
  symbol:   class ParagraphAble
  location: class Paragraph
__current-repo/java-solutions/markup/UnorderedList.java:12: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/OrderedList.java:10: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/ListItem.java:15: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/ListItem.java:18: error: cannot find symbol
        for (ListAble e: elements) {
             ^
  symbol:   class ListAble
  location: class ListItem
__current-repo/java-solutions/markup/Emphasis.java:14: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Emphasis.java:19: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Strikeout.java:14: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Strikeout.java:19: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Text.java:12: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Text.java:17: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Strong.java:14: error: method does not override or implement a method from a supertype
    @Override
    ^
__current-repo/java-solutions/markup/Strong.java:19: error: method does not override or implement a method from a supertype
    @Override
    ^
55 errors

